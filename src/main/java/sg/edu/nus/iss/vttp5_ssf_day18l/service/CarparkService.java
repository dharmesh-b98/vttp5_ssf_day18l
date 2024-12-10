package sg.edu.nus.iss.vttp5_ssf_day18l.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import sg.edu.nus.iss.vttp5_ssf_day18l.constants.Constant;
import sg.edu.nus.iss.vttp5_ssf_day18l.model.Carpark;

@Service
public class CarparkService {

    RestTemplate restTemplate = new RestTemplate();

    public List<Carpark> getCarparkList(){
        
        @SuppressWarnings("rawtypes")
        RequestEntity request = RequestEntity.get(Constant.carparkURL)
                                                .build();

        ResponseEntity<List<Carpark>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<Carpark>>(){});

        List<Carpark> carparkList = response.getBody();
        return carparkList;
    }
}
