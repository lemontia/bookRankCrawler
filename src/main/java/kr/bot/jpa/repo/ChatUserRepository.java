package kr.bot.jpa.repo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.bot.jpa.entity.ChatUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static kr.bot.jpa.entity.QChatUser.chatUser;

@Repository
public class ChatUserRepository extends QuerydslRepositorySupport {
    @PersistenceContext
    private EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    public ChatUserRepository(JPAQueryFactory jpaQueryFactory) {
        super(ChatUser.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    public void persist(ChatUser chatUser) {
        em.persist(chatUser);
    }

    public List<ChatUser> findAllByUsed() {
        return jpaQueryFactory.selectFrom(chatUser)
                .where(chatUser.useYn.eq(true))
                .fetch();
    }

    public ChatUser findOne(String chatName, Long chatId) {
        return jpaQueryFactory.selectFrom(chatUser)
                .where(chatUser.chatName.eq(chatName)
                        , chatUser.chatId.eq(chatId))
                .fetchOne();
    }
}
