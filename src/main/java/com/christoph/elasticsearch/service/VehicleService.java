package com.christoph.elasticsearch.service;

import com.christoph.elasticsearch.document.Vehicle;
import com.christoph.elasticsearch.helper.Indices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleService {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(VehicleService.class);

    private final RestHighLevelClient client;

    @Autowired
    public VehicleService(RestHighLevelClient client) {
        this.client = client;
    }

    public Boolean index(final Vehicle vehicle) {
        try {
            final String vehicleAsString = MAPPER.writeValueAsString(vehicle);

            final IndexRequest request = new IndexRequest(Indices.VEHICLE_INDEX);
            request.id(vehicle.getId());
            request.source(vehicleAsString, XContentType.JSON);

            final IndexResponse response = client.index(request, RequestOptions.DEFAULT);

            return response != null && response.status().equals(RestStatus.OK);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }

    public Vehicle getById(final String vehicleId) {
        try {
            final GetResponse documentFields = client.get(new GetRequest(Indices.VEHICLE_INDEX, vehicleId), RequestOptions.DEFAULT);

            assert documentFields != null;
            if (documentFields.isSourceEmpty()) {
                return null;
            }

            return MAPPER.readValue(documentFields.getSourceAsString(), Vehicle.class);
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}
