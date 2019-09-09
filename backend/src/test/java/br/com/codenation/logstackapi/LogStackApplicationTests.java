package br.com.codenation.logstackapi;

import br.com.codenation.logstackapi.dto.UserCreateDTOTest;
import br.com.codenation.logstackapi.model.entity.LogApplicationTest;
import br.com.codenation.logstackapi.model.entity.LogDetailTest;
import br.com.codenation.logstackapi.model.entity.LogTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UserCreateDTOTest.class,
		LogTest.class,
		LogDetailTest.class,
		LogApplicationTest.class
})
public class LogStackApplicationTests {


}
