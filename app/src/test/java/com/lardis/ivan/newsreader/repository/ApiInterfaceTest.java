package com.lardis.ivan.newsreader.repository;

import com.lardis.ivan.newsreader.TestUtils;
import com.lardis.ivan.newsreader.data.network.NewsApi;
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

    private NewsApi apiInterface;

    @Before
    public void setUp() throws Exception {

        server = new MockWebServer();
        server.start();
        final Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                TestUtils testUtils = new TestUtils();
                System.out.println(request.getPath());
                if (request.getPath().equals("/export/rss/lenta.xml")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("xml/gazeta.xml"));
                } else if (request.getPath().equals("/rss")) {
                    return new MockResponse().setResponseCode(200)
                            .setBody(testUtils.readString("xml/lenta.xml"));
                }
                return new MockResponse().setResponseCode(404);
            }
        };

        server.setDispatcher(dispatcher);
        HttpUrl baseUrl = server.url("/");
        ModelModule.BASE_LENTA_URL=baseUrl.toString();


        DI.init();
        apiInterface = DI.getInstance().componentManager().appComponent().getNewsApi();

    }


    @Test
    public void testGetRepositories() throws Exception {

        apiInterface.getItemsLenta().subscribe(rssLenta ->
        {
            assertEquals(rssLenta.getItems().size(), 200);
            assertEquals(rssLenta.getItems().get(0).getPubDate(), "Sun, 28 May 2017 18:43:40 +0300");
        });


    }




}