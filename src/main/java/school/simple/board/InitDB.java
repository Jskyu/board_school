package school.simple.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import school.simple.board.domain.BoardEntity;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() throws NoSuchAlgorithmException {
        initService.dbInit1();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() throws NoSuchAlgorithmException {
            List<BoardEntity> list = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                list.add(BoardEntity.builder()
                        .title("test" + i)
                        .content("content" + i)
                        .createTime(LocalDateTime.now())
                        .password(sha256("1234"))
                        .build());
            }

            list.forEach(em::persist);
        }

        private byte[] sha256(String password) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            return md.digest();
        }
    }
}
