package parsers.spring;

import org.junit.Test;
import parsers.ExpectedVersions;

import static org.junit.Assert.assertEquals;

public class SpringVersionParserTest {

    @Test
    public void testParseSpringVersion() throws Exception {
        String documentOrigin = getClass().getResource("/parsers/spring/spring.js").getPath();
        SpringVersionParser springVersionParser = new FakeSpringVersionParser(documentOrigin);
        assertEquals(new ExpectedVersions().getExpectedVersions().get("spring"), springVersionParser.parseVersion());
    }
}
