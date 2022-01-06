import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Collections.fill;
import static java.util.stream.Collectors.joining;


public class IPv6_소혜빈 {
    private static final String colon = ":";
    private static final String doubleColon = "::";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder ipv6 = new StringBuilder();
        String shortFormIpv6 = br.readLine();

        if(shortFormIpv6.indexOf(doubleColon) < 0) {
            ipv6.append(decompressIpv6ByRule1(shortFormIpv6.split(colon)));
        }
        else {
            int compressed0000Cnt = 8;
            String[] splittedIp = shortFormIpv6.split(doubleColon);

            String[] frontIp = splittedIp[0].split(colon);
            String decompressedFrontIp = decompressIpv6ByRule1(frontIp);
            compressed0000Cnt -= frontIp.length;
            ipv6.append(decompressedFrontIp);

            StringBuilder decompressedBackIp = new StringBuilder();
            if(splittedIp.length > 1) {
                String[] backIp = splittedIp[1].split(colon);
                decompressedBackIp.append(colon).append(decompressIpv6ByRule1(backIp));
                compressed0000Cnt -= backIp.length;
            }

            List<String> decompressed0000List = new ArrayList<>(asList(new String[compressed0000Cnt]));
            fill(decompressed0000List, "0000");
            StringBuilder middleIp = new StringBuilder(colon);
            middleIp.append(decompressed0000List.stream().collect(joining(colon)));
            ipv6.append(middleIp).append(decompressedBackIp);
        }

        System.out.println(ipv6);

        br.close();
    }

    private static String decompressIpv6ByRule1(String[] array) {
        return stream(array)
                .map(v -> String.format("%4s", v).replace(' ', '0'))
                .collect(joining(colon));
    }
}
