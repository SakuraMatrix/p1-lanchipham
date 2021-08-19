import org.junit.Assert;
import org.junit.Test;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import reactor.core.publisher.Flux;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ CassandraUnitTestExecutionListener.class })
@CassandraDataSet(value = { "budget.cql" })
@EmbeddedCassandra

public class BudgetTest {

    @Test
    public void testFlux() {
      Flux<String> flux = Flux.just("Spring 5");
      flux.subscribe(System.out::println);
    }

    @Test
    public void startAndExecuteCqlScript() throws Exception {
        CqlSession cs = EmbeddedCassandraServerHelper.getSession();

        ResultSet rs = cs.execute("SELECT * FROM budget.categories WHERE categoryId='8'");
        assertThat(rs.iterator().next().getString("name"), is("miscellaneous"));
    }

}
