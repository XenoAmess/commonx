package com.xenoamess.commons.java.net;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;


/**
 * @author XenoAmess
 */
public class URLStreamHandlerFactorySet implements URLStreamHandlerFactory {

    private boolean useRefuseHandleProtocolSet;

    private final Map<String, URLStreamHandlerFactory> urlStreamHandlerMap = new ConcurrentHashMap<>();
    private final Map<String, Double> defaultPriorityMap = new ConcurrentHashMap<>();
    private final Map<String, Map<String, Double>> specialPriorityMap =
            new ConcurrentHashMap<>();


    public URLStreamHandlerFactorySet() {
        this(true);
    }

    public URLStreamHandlerFactorySet(boolean useRefuseHandleProtocolSet) {
        this.useRefuseHandleProtocolSet = useRefuseHandleProtocolSet;
    }


    private static final Object streamHandlerLock = new Object();

    public static void wrapURLStreamHandlerFactory() throws NoSuchFieldException, IllegalAccessException {
        URLStreamHandlerFactorySet newFactory = new URLStreamHandlerFactorySet();
        try {
            URL.setURLStreamHandlerFactory(newFactory);
        } catch (Error e) {
            synchronized (streamHandlerLock) {
                Field factoryField = URL.class.getDeclaredField("factory");
                factoryField.setAccessible(true);
                URLStreamHandlerFactory factory = (URLStreamHandlerFactory) factoryField.get(null);
                newFactory.register(factory);
                factoryField.set(null, newFactory);
            }
        }
    }

    /**
     * Creates a new {@code URLStreamHandler} instance with the specified
     * protocol.
     *
     * @param protocol the protocol ("{@code ftp}",
     *                 "{@code http}", "{@code nntp}", etc.).
     * @return a {@code URLStreamHandler} for the specific protocol, or {@code
     * null} if this factory cannot create a handler for the specific
     * protocol
     * @see URLStreamHandler
     */
    @Override

    public URLStreamHandler createURLStreamHandler(String protocol) {
        if (this.useRefuseHandleProtocolSet && this.refuseHandleProtocolSet.contains(protocol)) {
            return null;
        }

        URLStreamHandler result = null;
        for (ImmutablePair<String, Double> entry : generateSortedURLStreamHandlerFactoryList(protocol)) {
            String urlStreamHandlerFactoryName = entry.getLeft();
            URLStreamHandlerFactory urlStreamHandlerFactory = this.urlStreamHandlerMap.get(urlStreamHandlerFactoryName);
            result = urlStreamHandlerFactory.createURLStreamHandler(protocol);
            if (result != null) {
                return result;
            }
        }

        if (this.useRefuseHandleProtocolSet) {
            this.refuseHandleProtocolSet.add(protocol);
        }
        return null;
    }

    public List<ImmutablePair<String, Double>> generateSortedURLStreamHandlerFactoryList(String protocol) {
        Map<String, Double> priorityMap = new HashMap<>(defaultPriorityMap);
        priorityMap.putAll(this.specialPriorityMap.get(protocol));
        List<ImmutablePair<String, Double>> result = new ArrayList<>();
        for (Map.Entry<String, Double> entry : priorityMap.entrySet()) {
            result.add(new ImmutablePair<>(entry.getKey(), entry.getValue()));
        }
        result.sort(new Comparator<ImmutablePair<String, Double>>() {
            @Override
            public int compare(ImmutablePair<String, Double> o1, ImmutablePair<String, Double> o2) {
                return -Double.compare(o1.getRight(), o2.getRight());
            }
        });
        return result;
    }

    public void register(URLStreamHandlerFactory urlStreamHandlerFactory) {
        this.register(urlStreamHandlerFactory.getClass().getCanonicalName(), urlStreamHandlerFactory, 1.0);
    }

    public void register(String urlStreamHandlerFactoryName, URLStreamHandlerFactory urlStreamHandlerFactory) {
        this.register(urlStreamHandlerFactoryName, urlStreamHandlerFactory, 1.0);
    }

    public void register(String urlStreamHandlerFactoryName, URLStreamHandlerFactory urlStreamHandlerFactory,
                         double priority) {
        if (urlStreamHandlerMap.containsKey(urlStreamHandlerFactoryName)) {
            throw new IllegalArgumentException("This URLStreamHandlerFactorySet already contains a " +
                    "URLStreamHandlerFactory named " + urlStreamHandlerFactoryName + "." +
                    "URLStreamHandlerFactorySet : " + this + "," +
                    "Existed URLStreamHandlerFactory : " + urlStreamHandlerMap.get(urlStreamHandlerFactoryName) +
                    "You want to register URLStreamHandlerFactory : " + urlStreamHandlerFactory);
        }
        refuseHandleProtocolReset();
        urlStreamHandlerMap.put(urlStreamHandlerFactoryName, urlStreamHandlerFactory);
        defaultPriorityMap.put(urlStreamHandlerFactoryName, priority);
        this.setPriority(urlStreamHandlerFactoryName, priority);
    }

    public void setPriority(String urlStreamHandlerFactoryName, String protocol, double priority) {
        Map<String, Double> urlStreamHandlerMap = specialPriorityMap.putIfAbsent(protocol, new ConcurrentHashMap<>());
        urlStreamHandlerMap.put(urlStreamHandlerFactoryName, priority);
    }

    public void setPriority(String urlStreamHandlerFactoryName, double priority) {
        defaultPriorityMap.put(urlStreamHandlerFactoryName, priority);
    }

    public double getPriority(String urlStreamHandlerFactoryName, String protocol) {
        Map<String, Double> urlStreamHandlerMap = specialPriorityMap.putIfAbsent(protocol, new ConcurrentHashMap<>());
        Double result = urlStreamHandlerMap.get(urlStreamHandlerFactoryName);
        if (result == null) {
            result = getPriority(urlStreamHandlerFactoryName);
        }
        return result;
    }

    public double getPriority(String urlStreamHandlerFactoryName) {
        return defaultPriorityMap.get(urlStreamHandlerFactoryName);
    }


    /**
     * refuseHandleProtocolSet.
     * <p>
     * A protocol will be put into this when we failed to find any
     * URLStreamHandlerFactory who can create a handler for this protocol
     * in this URLStreamHandlerFactorySet.
     * <p>
     * this set will be cleared when we register a new
     * <p>
     * this set and mechanism is only active when this.
     */
    private final Set<String> refuseHandleProtocolSet = new ConcurrentSkipListSet<>();

    /**
     * Register this protocol into refuseHandleProtocolSet.
     * <p>
     * This function will be called when we failed to find any
     * URLStreamHandlerFactory who can create a handler for this protocol
     * in this URLStreamHandlerFactorySet.
     *
     * @param protocol the protocol ("{@code ftp}",
     *                 "{@code http}", "{@code nntp}", etc.).
     * @return a {@code URLStreamHandler} for the specific protocol, or {@code
     * null} if this factory cannot create a handler for the specific
     * protocol
     * @see URLStreamHandler
     */
    private void refuseHandleProtocolRegister(String protocol) {
        if (!useRefuseHandleProtocolSet) {
            return;
        }
        refuseHandleProtocolSet.add(protocol);
    }

    private void refuseHandleProtocolReset() {
        refuseHandleProtocolSet.clear();
    }


    //getters and setters
    public boolean isUseRefuseHandleProtocolSet() {
        return useRefuseHandleProtocolSet;
    }

    public void setUseRefuseHandleProtocolSet(boolean useRefuseHandleProtocolSet) {
        this.useRefuseHandleProtocolSet = useRefuseHandleProtocolSet;
    }
}
