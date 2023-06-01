package jscode.board.domain;

import jscode.board.dto.board.BoardRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String head;

    @Lob
    private String body;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    private int recommends;

    public void editBoard(BoardRequestDto req) {
        head = req.getHead();
        body = req.getBody();
    }

    public void increaseLikeCount() {
        this.recommends += 1;
    }

    public void decreaseLikeCount() {
        this.recommends -= 1;
    }

    @Builder
    public Board(String head, String body, Member member) {
        this.head = head;
        this.body = body;
        this.member = member;
        this.recommends = 0;
    }


}
