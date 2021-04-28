package ferramong.pay.entities;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class Creditools {

    private int idDweller;

    private Creditools(int idDweller) {
        this.idDweller = idDweller;
    }

    public static Creditools of(int idDweller) {
        return new Creditools(idDweller);
    }

    public boolean creditUsingCreditools(double value) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  ");
        sb.append("\"idDweller\":");
        sb.append(" ");
        sb.append("\"" + idDweller + "\"");
        sb.append(",\n");
        sb.append("  ");
        sb.append("\"value\":");
        sb.append(" ");
        sb.append("\"" + String.valueOf(value) + "\"");
        sb.append("\n");
        sb.append("}");
        String body = sb.toString();

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange(
                "https://ferramong-creditools.herokuapp.com/wallet/credit/creditools",
                HttpMethod.PUT,
                requestEntity,
                String.class
        );
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();

        return (status == 200);
    }

    public boolean debit(double value) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  ");
        sb.append("\"idDweller\":");
        sb.append(" ");
        sb.append("\"" + idDweller + "\"");
        sb.append(",\n");
        sb.append("  ");
        sb.append("\"value\":");
        sb.append(" ");
        sb.append("\"" + String.valueOf(value) + "\"");
        sb.append("\n");
        sb.append("}");
        String body = sb.toString();

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange(
                "https://ferramong-creditools.herokuapp.com/wallet/debit",
                HttpMethod.PUT,
                requestEntity,
                String.class
        );
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();

        return (status == 200);
    }

    public double getBalance() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange(
                "https://ferramong-creditools.herokuapp.com/wallet/dweller/" + String.valueOf(idDweller),
                HttpMethod.GET,
                requestEntity,
                String.class
        );
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();

        return Double.parseDouble(responseEntity.getBody());
    }

    public boolean newWallet(int idDweller) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  ");
        sb.append("\"idDweller\":");
        sb.append(" ");
        sb.append("\"" + idDweller + "\"");
        sb.append(",\n");
        sb.append("}");
        String body = sb.toString();

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange(
                "https://ferramong-creditools.herokuapp.com/wallet",
                HttpMethod.POST,
                requestEntity,
                String.class
        );
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();

        return (status == 200);
    }
}
