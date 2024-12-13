package com.github.example.sampleurlhandlerfilter;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SampleControllerTest {
    @Nested
    @SpringBootTest
    @AutoConfigureMockMvc
    @SuppressWarnings("NonAsciiCharacters")
    class なにも使わないとき {
        private final MockMvc mockMvc;

        なにも使わないとき(@Autowired MockMvc mockMvc) {
            this.mockMvc = mockMvc;
        }

        @ParameterizedTest(name = "endpoint: /slash/with/ に {0} でアクセスすると Http-Status: {1} になる")
        @CsvSource({
                "/slash/with/, 200",
                "/slash/with, 404",
        })
        public void withSlash(String uri, int status) throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(uri))
                    .andExpect(status().is(status));
        }

        @ParameterizedTest(name = "endpoint: /slash/without に {0} でアクセスすると Http-Status: {1} になる")
        @CsvSource({
                "/slash/without/, 404",
                "/slash/without, 200",
        })
        public void withoutSlash(String uri, int status) throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(uri))
                    .andExpect(status().is(status));
        }
    }

    @Nested
    @SpringBootTest
    @AutoConfigureMockMvc
    @Import(TrailingSlashMatchConfiguration.class)
    @SuppressWarnings("NonAsciiCharacters")
    class PathMatchConfigurerを使うとき {
        private final MockMvc mockMvc;

        PathMatchConfigurerを使うとき(@Autowired MockMvc mockMvc) {
            this.mockMvc = mockMvc;
        }

        @ParameterizedTest(name = "endpoint: /slash/with/ に {0} でアクセスすると Http-Status: {1} になる")
        @CsvSource({
                "/slash/with/, 200",
                "/slash/with, 404",
        })
        public void withSlash(String uri, int status) throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(uri))
                    .andExpect(status().is(status));
        }

        @ParameterizedTest(name = "endpoint: /slash/without に {0} でアクセスすると Http-Status: {1} になる")
        @CsvSource({
                "/slash/without/, 200",
                "/slash/without, 200",
        })
        public void withoutSlash(String uri, int status) throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(uri))
                    .andExpect(status().is(status));
        }
    }

    @Nested
    @SpringBootTest
    @AutoConfigureMockMvc
    @Import(TrailingSlashHandleFilter.class)
    @SuppressWarnings("NonAsciiCharacters")
    class UrlHandlerFilterを使うとき {
        private final MockMvc mockMvc;

        UrlHandlerFilterを使うとき(@Autowired MockMvc mockMvc) {
            this.mockMvc = mockMvc;
        }

        @ParameterizedTest(name = "endpoint: /slash/with/ に {0} でアクセスすると Http-Status: {1} になる")
        @CsvSource({
                "/slash/with/, 404",
                "/slash/with, 404",
        })
        public void withSlash(String uri, int status) throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(uri))
                    .andExpect(status().is(status));
        }

        @ParameterizedTest(name = "endpoint: /slash/without に {0} でアクセスすると Http-Status: {1} になる")
        @CsvSource({
                "/slash/without/, 200",
                "/slash/without, 200",
        })
        public void withoutSlash(String uri, int status) throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get(uri))
                    .andExpect(status().is(status));
        }
    }
}
