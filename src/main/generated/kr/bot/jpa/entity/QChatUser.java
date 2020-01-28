package kr.bot.jpa.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatUser is a Querydsl query type for ChatUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChatUser extends EntityPathBase<ChatUser> {

    private static final long serialVersionUID = -1615471311L;

    public static final QChatUser chatUser = new QChatUser("chatUser");

    public final NumberPath<Long> chatId = createNumber("chatId", Long.class);

    public final StringPath chatName = createString("chatName");

    public final DateTimePath<java.time.LocalDateTime> createdDateTime = createDateTime("createdDateTime", java.time.LocalDateTime.class);

    public final StringPath firstName = createString("firstName");

    public final StringPath lastName = createString("lastName");

    public final BooleanPath useYn = createBoolean("useYn");

    public QChatUser(String variable) {
        super(ChatUser.class, forVariable(variable));
    }

    public QChatUser(Path<? extends ChatUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatUser(PathMetadata metadata) {
        super(ChatUser.class, metadata);
    }

}

