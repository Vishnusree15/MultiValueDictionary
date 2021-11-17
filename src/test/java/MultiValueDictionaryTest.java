import com.application.Dictionary;
import com.application.MultiValueDictionary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiValueDictionaryTest {
    Dictionary testMultiValueDict;

    @BeforeEach
    public void init() {
        testMultiValueDict = new MultiValueDictionary(new HashMap<>());
    }

    @Test
    void testGetKeys() throws Exception {
        testMultiValueDict.add("foo", "bar");
        testMultiValueDict.add("boom", "far");
        //order not guaranteed
        assertEquals(Arrays.asList("boom", "foo"), testMultiValueDict.getKeys());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testGetValues_KeyInValid(final String key) {
        final Exception exception = assertThrows(Exception.class, () -> testMultiValueDict.getValues(key));

        assertEquals("Key cannot be null, empty", exception.getMessage());
    }

    @Test
    void testGetValues_ValidKey() throws Exception {
        testMultiValueDict.add("foo", "bar");
        testMultiValueDict.add("foo", "far");
        //order not guaranteed
        assertEquals(Arrays.asList("bar", "far"), testMultiValueDict.getValues("foo"));
    }

    @Test
    void testGetValues_KeyNotExists() throws Exception {
        testMultiValueDict.add("foo", "bar");
        testMultiValueDict.add("foo", "far");
        final Exception exception = assertThrows(Exception.class, () -> testMultiValueDict.getValues("bad"));

        assertEquals("ERROR, key bad does not exist", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testAdd_KeyInValid(final String key) {
        final Exception exception = assertThrows(Exception.class, () -> testMultiValueDict.add(key, "valid"));

        assertEquals("Key cannot be null, empty", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testAdd_ValueInValid(final String value) {
        final Exception exception = assertThrows(Exception.class, () -> testMultiValueDict.add("valid", value));

        assertEquals("Value cannot be null, empty", exception.getMessage());
    }

    @Test
    void testAdd_ValidKeyValues() throws Exception{
        testMultiValueDict.add("foo", "bar");
        testMultiValueDict.add("foo", "baz");
        testMultiValueDict.add("boom", "baz");
        testMultiValueDict.add("boom", "far");
        assertEquals(2, testMultiValueDict.sizeofDictionary());
        Collection<String> expected = new ArrayList<>();
        expected.add("bar");
        expected.add("baz");
        assertEquals(expected, testMultiValueDict.getValues("foo"));
        expected.clear();
        expected.add("far");
        expected.add("baz");
        assertEquals(expected, testMultiValueDict.getValues("boom"));
    }

    @Test
    void testAdd_ValueExistsForKey() throws Exception{
        testMultiValueDict.add("foo", "bar");
        testMultiValueDict.add("foo", "baz");
        final Exception exception = assertThrows(Exception.class, () -> testMultiValueDict.add("foo", "bar"));
        assertEquals("ERROR, member already exists for key foo", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testRemove_KeyInValid(final String key) {
        final Exception exception = assertThrows(Exception.class, () -> testMultiValueDict.add(key, "valid"));

        assertEquals("Key cannot be null, empty", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testRemove_ValueInValid(final String value) {
        final Exception exception = assertThrows(Exception.class, () -> testMultiValueDict.add("valid", value));

        assertEquals("Value cannot be null, empty", exception.getMessage());
    }

    @Test
    void testRemove_ValidKeyValues() throws Exception{
        testMultiValueDict.add("foo", "bar");
        testMultiValueDict.add("foo", "baz");
        testMultiValueDict.add("boom", "baz");
        testMultiValueDict.remove("foo", "bar");
        Collection<String> expected = new ArrayList<>();
        expected.add("baz");
        assertEquals(expected, testMultiValueDict.getValues("foo"));
    }

    @Test
    void testRemove_ValidKeyValueNotExist() throws Exception{
        testMultiValueDict.add("foo", "baz");
        testMultiValueDict.add("boom", "baz");
        final Exception exception = assertThrows(Exception.class, () -> testMultiValueDict.remove("foo", "bar"));

        assertEquals("ERROR, value bar does not exist.", exception.getMessage());
    }

    @Test
    void testRemove_ValidKeyValueSetEmpty() throws Exception{
        testMultiValueDict.add("foo", "baz");
        testMultiValueDict.remove("foo", "baz");
        assertEquals(0, testMultiValueDict.sizeofDictionary());
    }

    @Test
    void testRemove_ValidKeyNotExists() throws Exception{
        testMultiValueDict.add("foo", "baz");
        final Exception exception = assertThrows(Exception.class, () -> testMultiValueDict.remove("boom", "bar"));

        assertEquals("ERROR, key boom does not exist.", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testRemoveAll_KeyInValid(final String key) {
        final Exception exception = assertThrows(Exception.class, () -> testMultiValueDict.add(key, "valid"));

        assertEquals("Key cannot be null, empty", exception.getMessage());
    }

    @Test
    void testRemoveAll_ValidKey() throws Exception{
        testMultiValueDict.add("foo", "baz");
        testMultiValueDict.add("foo", "bar");
        assertEquals(1, testMultiValueDict.sizeofDictionary());
        testMultiValueDict.removeAll("foo");
        assertEquals(0, testMultiValueDict.sizeofDictionary());
    }

    @Test
    void testRemoveAll_ValidKeyNotExists() {
        final Exception exception = assertThrows(Exception.class, () -> testMultiValueDict.remove("boom", "bar"));

        assertEquals("ERROR, key boom does not exist.", exception.getMessage());
    }

    @Test
    void testClear() throws Exception{
        testMultiValueDict.add("foo", "baz");
        testMultiValueDict.add("bang", "bar");
        assertEquals(2, testMultiValueDict.sizeofDictionary());
        testMultiValueDict.clear();
        assertEquals(0, testMultiValueDict.sizeofDictionary());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testKeyExists_KeyInValid(final String key) {
        final Exception exception = assertThrows(Exception.class, () -> testMultiValueDict.isKeyExists(key));

        assertEquals("Key cannot be null, empty", exception.getMessage());
    }

    @Test
    void testKeyExists_KeyValid() throws Exception{
        testMultiValueDict.add("foo", "baz");
        testMultiValueDict.add("bang", "bar");
        assertTrue(testMultiValueDict.isKeyExists("foo"));
        assertFalse(testMultiValueDict.isKeyExists("bad"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testValueExists_KeyInValid(final String key) {
        final Exception exception = assertThrows(Exception.class, () -> testMultiValueDict.isValueExists(key, "valid"));

        assertEquals("Key cannot be null, empty", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testValueExists_ValueInValid(final String value) {
        final Exception exception = assertThrows(Exception.class, () -> testMultiValueDict.isValueExists("key", value));

        assertEquals("Value cannot be null, empty", exception.getMessage());
    }

    @Test
    void testValueExists_ValueValid() throws Exception{
        assertFalse(testMultiValueDict.isValueExists("foo", "baz"));
        testMultiValueDict.add("foo", "baz");
        assertTrue(testMultiValueDict.isValueExists("foo", "baz"));
        assertFalse(testMultiValueDict.isValueExists("foo", "bar"));

    }

    @Test
    void testgetAllValues() throws Exception{
        assertTrue(testMultiValueDict.getAllValues().isEmpty());
        testMultiValueDict.add("foo", "baz");
        testMultiValueDict.add("foo", "bar");
        //order not guaranteed
        assertEquals(Arrays.asList("bar", "baz"), testMultiValueDict.getAllValues());
        testMultiValueDict.add("bom", "baz");
        testMultiValueDict.add("boom", "bar");
        //order not guaranteed
        assertEquals(Arrays.asList("baz", "bar", "bar", "baz"), testMultiValueDict.getAllValues());

    }

    @Test
    void testItems() throws Exception{
        assertTrue(testMultiValueDict.getAllValues().isEmpty());
        testMultiValueDict.add("foo", "baz");
        testMultiValueDict.add("foo", "bar");
        Map<String, Set<String>> expectedOutput = new HashMap<>();
        Set<String> expectedValues = new HashSet<>();
        expectedValues.add("baz");
        expectedValues.add("bar");
        expectedOutput.put("foo", expectedValues);
        //order not guaranteed
        assertEquals(expectedOutput, testMultiValueDict.getItems());
        testMultiValueDict.add("boom", "baz");
        testMultiValueDict.add("boom", "bar");
        expectedOutput.put("boom", expectedValues);
        //order not guaranteed
        assertEquals(expectedOutput, testMultiValueDict.getItems());

    }
}
