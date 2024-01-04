package strings;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;
import java.util.Base64;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://brunch.co.kr/@simplebrunch/13">참고 자료</a>
 */
public class EncodingDecodingTest {
    @DisplayName("인코딩한 byte[] 배열을 이용해서 String을 생성하면 디코딩된 String 객체가 생성된다.")
    @Test
    void stringDecodingTest() {
        // given
        String str = "안녕하세요";
        byte[] encodedStr = str.getBytes(Charset.defaultCharset());  // 인코딩한 바이트 시퀀스

        // when
        String decodedStr = new String(encodedStr);

        // then
        assertThat(str).isEqualTo(decodedStr);
    }

    @DisplayName("일부만 디코딩할 수 있다. (한글의 한 글자는 3byte를 차지한다.)")
    @Test
    void stringDecodingTest2() {
        // given
        String str = "안녕하세요";
        byte[] encodedStr = str.getBytes(Charset.defaultCharset());  // 인코딩한 바이트 시퀀스

        // when
        String first3Bytes = new String(encodedStr, 0, 3);  // 0번째부터 3개의 바이트만 디코딩
        System.out.println("first3Bytes = " + first3Bytes);

        String second3Bytes = new String(encodedStr, 3, 3);  // 3번째부터 3개의 바이트만 디코딩
        System.out.println("second3Bytes = " + second3Bytes);

        String third3Bytes = new String(encodedStr, 6, 3);  // 6번째부터 3개의 바이트만 디코딩
        System.out.println("third3Bytes = " + third3Bytes);

        // then
        assertThat(first3Bytes).isEqualTo("안");
        assertThat(second3Bytes).isEqualTo("녕");
        assertThat(third3Bytes).isEqualTo("하");
    }

    @DisplayName("Base64 인코딩/디코딩 테스트")
    @Test
    void base64Test() {
        // given
        String 안녕하세요 = "안녕하세요";
        byte[] 안녕하세요Bytes = 안녕하세요.getBytes(Charset.defaultCharset());

        // when
        byte[] encodedByte = Base64.getEncoder().encode(안녕하세요Bytes);  // byte -> base64 encoded byte
        String encodedStr = Base64.getEncoder().encodeToString(안녕하세요Bytes);  // byte -> base64 encoded String
        String decodedBase64EncodedStr = new String(encodedByte);  // base64 encoded byte -> base64 encoded String

        byte[] decodedByte = Base64.getDecoder().decode(encodedStr);  // base64 encoded String -> base64 decoded byte
        String decodedBase64DecodedStr = new String(decodedByte);  // base64 decoded byte -> base64 decoded String

        // then
        System.out.println("decodedBase64EncodedStr = " + decodedBase64EncodedStr);
        assertThat(encodedStr).isEqualTo(decodedBase64EncodedStr);

        System.out.println("decodedBase64DecodedStr = " + decodedBase64DecodedStr);
        assertThat(decodedBase64DecodedStr).isEqualTo(안녕하세요);
    }
}
