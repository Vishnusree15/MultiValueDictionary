package com.application;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This Class implements {@link Dictionary}.
 */
public class MultiValueDictionary implements Dictionary {

    private final Map<String, Set<String>> multiValueDict;

    /**
     * constructor for defining the instance and map is not initialized to have dependency injection.
     * @param multiValueDict
     */
    public MultiValueDictionary(Map<String, Set<String>> multiValueDict) {
        this.multiValueDict = multiValueDict;
    }

    /**
     * {@inheritDoc}
     */
    public List<String> getKeys() {
        return multiValueDict.keySet().stream().collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    public List<String> getValues(String key) throws Exception {
        validKey(key);
        if (isKeyExists(key)) {
            return multiValueDict.get(key).stream().collect(Collectors.toList());
        } else {
            throw new Exception(String.format("ERROR, key %s does not exist", key));
        }

    }

    /**
     * {@inheritDoc}
     */
    public void add(String key, String value) throws Exception {
        validKey(key);
        validValue(value);

        Set<String> members = new HashSet<>();
        if (isKeyExists(key)) {
            members = multiValueDict.get(key);
            if (!members.contains(value)) {
                members.add(value);
            } else {
                throw new Exception(String.format("ERROR, member already exists for key %s", key));
            }
        } else {
            members.add(value);
        }
        multiValueDict.put(key, members);

    }

    /**
     * {@inheritDoc}
     */
    public void remove(String key, String value) throws Exception{
        validKey(key);
        validValue(value);

        Set<String> members;
        if (isKeyExists(key)) {
            members = multiValueDict.get(key);
            if (members.contains(value)) {
                members.remove(value);

            } else {
                throw new Exception(String.format("ERROR, value %s does not exist.", value));
            }
            if (members.isEmpty())
                removeAll(key);
             else
                multiValueDict.put(key, members);
        } else {
            throw new Exception(String.format("ERROR, key %s does not exist.", key));
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeAll(String key) throws Exception {
        validKey(key);
        if (isKeyExists(key))
            multiValueDict.remove(key);
         else
            throw new Exception(String.format("ERROR, key {0} does not exist", key));
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        multiValueDict.clear();
    }

    /**
     * {@inheritDoc}
     */
    public int sizeofDictionary() {
        return multiValueDict.size();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isKeyExists(String key) throws Exception {
        validKey(key);
        return multiValueDict.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isValueExists(String key, String value) throws Exception {
        validKey(key);
        validValue(value);
        return isKeyExists(key) ? multiValueDict.get(key).contains(value) : false;
    }

    /**
     * {@inheritDoc}
     */
    public List<String> getAllValues() {
        return multiValueDict.entrySet().stream().flatMap(e -> e.getValue().stream()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, Set<String>> getItems() {
        return multiValueDict;
    }

    /**
     * Checks on the validity of input key.
     * @param key {@link String} input key to be validated.
     * @throws Exception throws when input key is not valid.
     */
    private void validKey(String key) throws Exception {
        if (key == null || key.isEmpty())
            throw new Exception("Key cannot be null, empty");
    }

    /**
     * Checks on the validity of input value.
     * @param value {@link String} input value to be validated.
     * @throws Exception throws when input value is not valid.
     */
    private void validValue(String value) throws Exception {
        if (value == null || value.isEmpty())
            throw new Exception("Value cannot be null, empty");
    }
}
