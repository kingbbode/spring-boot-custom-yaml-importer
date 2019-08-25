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
@ActiveProfiles("real")
@RunWith(SpringRunner.class)
@SpringBootTest
public class RealStubPropertiesLoadTest {

    @Autowired
    private Test1Properties test1Properties;

    @Autowired
    private Test2Properties test2Properties;

    @Autowired
    private Test3Properties test3Properties;

    @Autowired
    private Test4Properties test4Properties;

    @Test
    public void  real_properties() {
        assertEquals("d", test1Properties.getTest());
        assertEquals("d", test2Properties.getTest());
        assertEquals("d", test3Properties.getTest());
        assertEquals("d", test4Properties.getTest());
    }
}
