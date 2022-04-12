package com.github.kingbbode.custom.yaml.importer.processor.properties;


import com.github.kingbbode.custom.yaml.importer.processor.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ActiveProfiles("beta")
@SpringBootTest
public class DevPropertiesLoadTest {

    @Autowired
    private Test1Properties test1Properties;

    @Autowired
    private Test2Properties test2Properties;

    @Autowired
    private Test3Properties test3Properties;

    @Autowired
    private Test4Properties test4Properties;

    @Autowired
    private Test5Properties test5Properties;

    @Autowired
    private Test6Properties test6Properties;

    @Autowired
    private Test7Properties test7Properties;

    @Autowired
    private Test8Properties test8Properties;

    @Test
    public void dev_properties() {
        assertEquals("b", test1Properties.getTest());
        assertEquals("b", test2Properties.getTest());
        assertEquals("b", test3Properties.getTest());
        assertEquals("b", test4Properties.getTest());
        assertEquals("b", test5Properties.getTest());
        assertEquals("b", test6Properties.getTest());
        assertEquals("b", test7Properties.getTest());
        assertEquals("b", test8Properties.getTest());
    }
}
