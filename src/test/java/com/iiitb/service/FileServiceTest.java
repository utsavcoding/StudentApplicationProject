package com.iiitb.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FileServiceTest {

    @InjectMocks
    FileService fileService= Mockito.spy(new FileService());

    @Mock
    FileInputStream inputStream;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testUploadSuccess() throws IOException {
        boolean expected=true;
        when(inputStream.read(Mockito.any())).thenReturn(-1);
        boolean actual=fileService.upload(inputStream,"test");
        assertEquals(expected,actual);
    }

    /*
    IOException in junit
    @Test
    public void testUploadFailure() throws IOException {
        boolean expected=false;
        when(inputStream.read(Mockito.any())).thenThrow(new IOException());
        boolean actual=fileService.upload(inputStream,"test");
        assertEquals(expected,actual);
    }*/
}
