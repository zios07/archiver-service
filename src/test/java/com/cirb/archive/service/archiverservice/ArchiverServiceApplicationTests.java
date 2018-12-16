package com.cirb.archive.service.archiverservice;


import com.cirb.archive.domain.vo.SearchVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArchiverServiceApplicationTests {

  @Rule
  public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
      .apply(documentationConfiguration(this.restDocumentation))
      .alwaysDo(document("{method-name}",
        preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
      .build();
  }

  @Test
  public void getAll() throws Exception {
    this.mockMvc.perform(get("/api/archives").accept(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(status().isOk()).andDo(document("get-all",
      responseFields(
        fieldWithPath("[].id")
          .description("The archive's id"),
        fieldWithPath("[].content")
          .description("The content of the archive as byte array"),
        fieldWithPath("[].date")
          .description("The date of the creation of archive"),
        fieldWithPath("[].extension")
          .description("Archive extension"),
        fieldWithPath("[].fileName")
          .description("The archive file name")
      )));
  }


  @Test
  public void search() throws Exception {
    this.mockMvc.perform(post("/api/archives/search").accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE)
      .content(objectMapper.writeValueAsString(new SearchVO())))
      .andExpect(status().isOk()).andDo(document("search",
      requestFields(
        fieldWithPath("dateFrom")
          .description("The minimal date of the archive"),
        fieldWithPath("dateTo")
          .description("The max date of the archive"),
        fieldWithPath("timestamp").
          description("The exact date of the archive, used only if neither dateFrom nor dateTo is specified"),
        fieldWithPath("keyValue").
          description("Used to retrieve only archives which keyValue attribute is equals to the specified keyValue attribute"),
        fieldWithPath("transactionId").
          description("Used to retrieve only archives which transactionId attribute is equals to the specified keyValue transactionId"))
    ));
  }
}
