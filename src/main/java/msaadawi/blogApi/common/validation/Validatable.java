package msaadawi.blogApi.common.validation;

import msaadawi.blogApi.common.exception.NoSuchPropertyException;

public interface Validatable {

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
