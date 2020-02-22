/*
 * My-Wine-Cellar, copyright 2020
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 */

package info.mywinecellar.api;

import info.mywinecellar.api.service.ProducerRestService;
import info.mywinecellar.model.Producer;
import info.mywinecellar.service.ProducerService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class ProducerRestControllerTest {

    @InjectMocks
    ProducerRestController controller;

    @Mock
    ProducerService service;

    @Mock
    ProducerRestService restService;

    Producer opusOne;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        opusOne = new Producer();
        opusOne.setId(1L);

        opusOne.setName("Opus One");
        opusOne.setWines(Collections.emptyList());

        mockMvc = standaloneSetup(controller).build();
    }

    @Test
    void producerEditPut() throws Exception {
        given(service.findById(opusOne.getId())).willReturn(opusOne);

        opusOne.setName("Opus One Winery");

        MockHttpServletResponse response = mockMvc.perform(put("/api/producer/{id}/edit", opusOne.getId())
                .content(new ObjectMapper().writeValueAsString(opusOne))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals("Opus One Winery", opusOne.getName());
        assertEquals(1L, opusOne.getId());
        assertThat(response.getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());
    }

    @Test
    public void testImagePath() {
        String path = "src/test/resources";
        String image = "opus_one.jpg";

        File file = new File(path, image);
        String absolutePath = file.getAbsolutePath();

        System.out.println(absolutePath);

        assertTrue(file.exists());
    }

    @Test
    public void producerImagePost() throws Exception {

        assertNull(opusOne.getImage());
        opusOne.setImage(readBytesFromFile());

        MockMultipartFile file = new MockMultipartFile("file", readBytesFromFile());

        MockHttpServletResponse response = mockMvc.perform(put("/api/producer/1/image", opusOne.getId(), file)
                .contentType(MediaType.MULTIPART_FORM_DATA)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(opusOne.getImage()).isNotNull();
    }

    private static byte[] readBytesFromFile() {

        FileInputStream fileInputStream = null;
        byte[] bytesArray = null;
        String path = "src/test/resources/opus_one.jpg";

        try {
            File file = new File(path);
            bytesArray = new byte[(int) file.length()];

            //read file into bytes[]
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytesArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytesArray;
    }

}
