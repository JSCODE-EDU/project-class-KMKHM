package jscode.board.domain;

import jscode.board.dto.BoardRequestDto;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String head;

    @Lob
    private String body;

    public void editBoard(BoardRequestDto req) {
        head = req.getHead();
        body = req.getBody();
    }

    @Builder
    public Board(String head, String body) {
        this.head = head;
        this.body = body;
    }


}
