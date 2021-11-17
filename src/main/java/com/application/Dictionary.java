package com.application;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Dictionary<K, V> {

    /**
     * Returns all the keys in dictionary.
     * @return {@link List} of {@link String} keys.
     */
    public List<String> getKeys();

    /**
     * Returns collection of string values for the given key and gives error if the key doesn't exist.
     * @param key {@link String} key for value to be returned.
     * @return {@link List} of {@link String} values related to the given key.
     * @throws Exception thrown when key doesn't exists.
     */
    public List<String> getValues(String key) throws Exception;

    /**
     * Adds a value to a collection for a given key and gives error if the value already exists for key.
     * @param key {@link String} given key
     * @param value {@link String} value related to key.
     * @throws Exception thrown when value exists for given key.
     */
    public void add(String key, String value) throws Exception;

    /**
     * Removes a value of given key and key has empty set then key is removed from the dictionary. Throws error if the key or value does not exist.
     * @param key {@link String} given key
     * @param value {@link String} value related to key.
     * @throws Exception thrown when key or value does not exists.
     */
    public void remove(String key, String value) throws Exception;

    /**
     * Removes all values for a key and removes the key from the dictionary and throws error if the key does not exist.
     * @param key {@link String} given key to be removed.
     * @throws Exception thrown when key does not exists.
     */
    public void removeAll(String key) throws Exception;

    /**
     * Removes all keys and values from dictionary.
     */
    public void clear();

    /**
     * Gives the number of keys present in dictionary.
     * @return {@link Integer} number of key elements of dictionary.
     */
    public int sizeofDictionary();

    /**
     * Returns whether a key exists or not.
     * @param key {@link String} to be searched for.
     * @return {@link Boolean} if key present or not.
     * @throws Exception throws when input key is not valid.
     */
    public boolean isKeyExists(String key) throws Exception;

    /**
     * Returns whether a member exists within a key. Returns false if the key does not exist.
     * @param key {@link String} given.
     * @param value {@link String} to be searched for.
     * @return {@link Boolean} if value present for key or not.
     * @throws Exception thrown when key or value are not valid.
     */
    public boolean isValueExists(String key, String value) throws Exception;

    /**
     * Returns all the values in the dictionary and nothing if there are none. Order is not guaranteed.
     * @return {@link Collection} retrieves all values of a dictionary.
     */
    public Collection<String> getAllValues();

    /**
     * Returns all keys in the dictionary along with values and nothing if there are none. Order is not guaranteed.
     * @return {@link Collection} retrieves all elements of a dictionary.
     */
    public Map<String, Set<String>> getItems();

}
