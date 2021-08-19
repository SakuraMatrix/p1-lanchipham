// import org.junit.Test;
// import org.cassandraunit.spring.CassandraDataSet;
// //import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
// import org.junit.runner.RunWith;
// //import org.springframework.test.context.TestExecutionListeners;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
// import reactor.core.publisher.Flux;
// import com.github.SakuraMatrix.p1lanchipham.AppConfig;
// import org.junit.jupiter.api.BeforeAll;
// //import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestInstance;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.ApplicationContext;
// import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
// import org.springframework.test.web.reactive.server.WebTestClient;

// @RunWith(SpringJUnit4ClassRunner.class)
// @CassandraDataSet(value = { "budget.cql" })
// @SpringJUnitConfig(classes = AppConfig.class)
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)

// public class BudgetTest {

//   @Autowired
//   ApplicationContext context;

//   WebTestClient client;

//   @BeforeAll
//   public void setup() {
//     this.client = WebTestClient.bindToApplicationContext(this.context).configureClient().build();
//   }

//   @Test
//   public void getAllCategoriesOK() throws Exception {
//     this.client.get().uri("/categories").exchange().expectStatus().isOk();
//   }

//   @Test
//   public void getOneCategoryOk() throws Exception {
//     this.client.get().uri("/categories/8").exchange().expectStatus().isOk();
//   }

//   @Test
//   public void testFlux() {
//   Flux<String> flux = Flux.just("Spring 5");
//   flux.subscribe(System.out::println);
//   }

// }
