import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Collections.fill;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


public class IPv6_소혜빈 {
    private static final String colon = ":";
    private static final String doubleColon = "::";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String shortFormIpv6 = br.readLine();
        List<String> arrIpv6 = new ArrayList<>();

        String[] splittedIp = shortFormIpv6.split(doubleColon);
        List<String> decompressedFrontIp = getDecompressedFrontIp(splittedIp);
        List<String> decompressedBackIp = getDecompressedBackIp(splittedIp);

        int compressed0000Cnt = 8 - (decompressedFrontIp.size() + decompressedBackIp.size());
        List<String> decompressed0000List = getDecompressed0000List(compressed0000Cnt);

        arrIpv6.addAll(decompressedFrontIp);
        arrIpv6.addAll(decompressed0000List);
        arrIpv6.addAll(decompressedBackIp);
        String ipv6 = arrIpv6.stream().collect(joining(colon)).toString();

        System.out.println(ipv6);

        br.close();
    }

    private static List<String> decompressIpv6ByRule1(String[] array) {
        return stream(array)
                .map(v -> String.format("%4s", v).replace(' ', '0'))
                .collect(toList());
    }

    private static List<String> getDecompressedFrontIp(String[] splittedIp) {
        String[] frontIp = splittedIp[0].split(colon);
        List<String> decompressedFrontIp = decompressIpv6ByRule1(frontIp);
        return decompressedFrontIp;
    }

    private static List<String> getDecompressedBackIp(String[] splittedIp) {
        List<String> decompressedBackIp = new ArrayList<>();

        if (splittedIp.length > 1) {
            String[] backIp = splittedIp[1].split(colon);
            decompressedBackIp.addAll(decompressIpv6ByRule1(backIp));
        }
        return decompressedBackIp;
    }

    private static List<String> getDecompressed0000List(int compressed0000Cnt) {
        List<String> decompressed0000List = new ArrayList<>(asList(new String[compressed0000Cnt]));
        fill(decompressed0000List, "0000");
        return decompressed0000List;
    }
}
