package msaadawi.blogApi.commons.error;

import msaadawi.blogApi.commons.exception.NoSuchPropertyException;

public interface ValidatedBean {

    String getResourceName();

    default boolean hasField(String fieldName) {
        try {
            getFieldValueByName(fieldName);
            return true;
        } catch (NoSuchPropertyException ex) {
            return false;
        }
    }

    Object getFieldValueByName(String fieldName) throws NoSuchPropertyException;
}
