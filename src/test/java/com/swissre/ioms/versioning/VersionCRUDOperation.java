package com.swissre.ioms.versioning;

public interface VersionCRUDOperation {
    void create();
    void updateAll();
    void updateById();
    void selectById();
    void selectAll();
    void deleteAll();
    void deleteById();
}
