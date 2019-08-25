package com.github.kingbbode.custom.yaml.importer.processor.properties;

import com.github.kingbbode.custom.yaml.importer.processor.Test1Properties;
import com.github.kingbbode.custom.yaml.importer.processor.Test2Properties;
import com.github.kingbbode.custom.yaml.importer.processor.Test3Properties;
import com.github.kingbbode.custom.yaml.importer.processor.Test4Properties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@Slf4j
@ActiveProfiles("stub")
@RunWith(SpringRunner.class)
@SpringBootTest
public class StubPropertiesLoadTest {

    @Autowired
    private Test1Properties test1Properties;

    @Autowired
    private Test2Properties test2Properties;

    @Autowired
    private Test3Properties test3Properties;

    @Autowired
    private Test4Properties test4Properties;

    @Test
    public void  stub_properties() {
        assertEquals("c", test1Properties.getTest());
        assertEquals("a", test2Properties.getTest());
        assertEquals("b", test3Properties.getTest());
        assertEquals("b", test4Properties.getTest());
    }
}
