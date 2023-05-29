package jscode.board.domain;

import jscode.board.dto.board.BoardRequestDto;
import lombok.*;

import javax.persistence.*;

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

    public void editBoard(BoardRequestDto req) {
        head = req.getHead();
        body = req.getBody();
    }

    @Builder
    public Board(String head, String body, Member member) {
        this.head = head;
        this.body = body;
        this.member = member;
    }


}
