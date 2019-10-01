package br.com.codenation.logstackapi.mapper;

import br.com.codenation.logstackapi.builders.LogApplicationBuilder;
import br.com.codenation.logstackapi.builders.LogBuilder;
import br.com.codenation.logstackapi.builders.TriggerBuilder;
import br.com.codenation.logstackapi.dto.response.TriggerResponseDTO;
import br.com.codenation.logstackapi.mappers.TriggerSearchMapper;
import br.com.codenation.logstackapi.model.entity.Log;
import br.com.codenation.logstackapi.model.entity.Trigger;
import br.com.codenation.logstackapi.model.entity.TriggerSearch;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TriggerSearchMapperTest {

    @Autowired
    TriggerSearchMapper mapper;

    @Test
    public void dadoLog_quandoExecutarMapper_entaoDeveRetornarTriggerSearch(){
        Log log = LogBuilder.umLog().arquivado().emDesenvolvimento().comLevelDebug().build();

        TriggerSearch triggerSearch = mapper.map(log);

        Assert.assertThat(triggerSearch, Matchers.notNullValue());
        Assert.assertThat(triggerSearch.getAppName(), Matchers.equalTo(log.getApplication().getName()));
        Assert.assertThat(triggerSearch.getLevel(), Matchers.equalTo(log.getDetail().getLevel()));
    }
}
