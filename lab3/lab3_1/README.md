# Lab 3

## 3.1

### a)

Some examples that use expressive AssertJ method chaining are:

- Line 37 in A_EmployeeRepositoryTest.java
- Line 66 in E_EmployeeRestControllerTemplateIT.java

### b)

In file B_EmployeeService_UnitTest.java, EmployeeRepository is mocked to avoid using a real repository and database, limiting the scope of the test to the EmployeeService class (unit test).

### c)

The @Mock annotation belongs to the Mockito framework, unlike the @MockBean annotation. Functionally, they also differ: @Mock instantiates a mock with the interface of the annotated object, while @MockBean injects required beans in a Spring bean as mocks.

### d)

The file "application-integrationtest.properties" indicates a real database source to be used in integration tests, such as D and E.

### e)

**\C**

In test C, @WebMvcTest loads a simplified test-friendly web framework, it doesn't load the entire application. A MockMvc reference is injected to access components and perform server-side testing. In this case it's being used to perform requests to the controller. 

Unlike the next tests, there's no repository component involved (no data persistence) and the service component is mocked to further localize the test to the Controller.

**\D**

In test D, @SpringBootTest is used with arguments webEnvironment = WebEnvironment.MOCK and classes = EmployeeMngrApplication.class to create a simplified web framework (WebEnvironment.MOCK) with a designated app (EmployeeMngrApplication.class).

Similarly to test C, it also uses a MockMvc reference object to access components and perform server-side testing.

Unlike test C, it now uses a real repository component, integrating it in testing.

**\E**

In test E, @SpringBootTest is used with the argument webEnvironment = WebEnvironment.RANDOM_PORT, creating the full web context. 

While mostly similar to the previous test, it uses a TestRestTemplate as a REST client to create realistic requests instead of a special testing servlet (MockMvc) for server-side testing.

