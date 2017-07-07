package com.lardis.ivan.newsreader.data.repository;

import com.lardis.ivan.newsreader.TestUtils;
import com.lardis.ivan.newsreader.data.network.api.LTechApi;
import com.lardis.ivan.newsreader.data.network.model.LTechModelNW;
import com.lardis.ivan.newsreader.di.DI;
import com.lardis.ivan.newsreader.di.model.ModelModule;

import org.junit.Before;
import org.junit.Test;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.junit.Assert.assertEquals;

public class ApiInterfaceTest {

    private MockWebServer server;

    private LTechApi apiInterface;

    @Before
    public void setUp() throws Exception {

        server = new MockWebServer();
        server.start();
        final Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                TestUtils testUtils = new TestUtils();
                System.out.println(request.getPath());

                return new MockResponse().setResponseCode(200)
                        .setBody(testUtils.readString("gson/test"));
            }
        };

        server.setDispatcher(dispatcher);
        HttpUrl baseUrl = server.url("/");
        ModelModule.BASE_URL =baseUrl.toString();


        DI.init();
        apiInterface = DI.getInstance().componentManager().appComponent().getNewsApi();

    }


    @Test
    public void testGetRepositories() throws Exception {

        apiInterface.getLTech().subscribe(lTechModelNWs ->
        {

            for (LTechModelNW lTechModelNW : lTechModelNWs) {

                System.out.println(lTechModelNW.getTitle());

            }
            assertEquals(lTechModelNWs.size(), 8);
        });


    }




}