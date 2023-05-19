package org.projects.development.enums;

public enum RequestField {
    PATH("path"),
    RECURSIVE("recursive"),
    INCLUDE_MEDIA_INFO("include_media_info"),
    INCLUDE_DELETED("include_deleted"),
    INCLUDE_HAS_EXPLICIT_SHARED_MEMBERS("include_has_explicit_shared_members"),
    INCLUDE_MOUNTED_FOLDERS("include_mounted_folders"),
    INCLUDE_NON_DOWNLOADABLE_FILES("include_non_downloadable_files");

    private final String fieldName;

    RequestField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}

