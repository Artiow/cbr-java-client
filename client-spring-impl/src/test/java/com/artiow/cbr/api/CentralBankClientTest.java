package com.artiow.cbr.api;

import com.artiow.cbr.api.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RestTemplate.class})
public class CentralBankClientTest {

    @Autowired
    private RestTemplate restTemplate;

    private CentralBankClient client;


    @Before
    public void setUp() {
        client = new CentralBankClient(restTemplate);
    }


    @Test
    public void testDaily() throws Exception {
        // act
        val result = client.daily(LocalDate.now());

        // assert
        log.debug(JsonUtil.asPrettyJsonString(result));
        Assert.assertNotNull(result);
    }
}
