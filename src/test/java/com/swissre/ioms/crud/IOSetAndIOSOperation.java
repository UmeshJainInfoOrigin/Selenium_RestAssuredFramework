package com.swissre.ioms.crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;

public interface IOSetAndIOSOperation {
    void create() ;
    void updateAll();
    void updateById();
    void selectById();
    void selectAll();
    void deleteAll();
    void deleteById();
    boolean compare();
}
