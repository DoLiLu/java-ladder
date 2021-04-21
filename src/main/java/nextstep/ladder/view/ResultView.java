package nextstep.ladder.view;

import nextstep.ladder.common.Constants;
import nextstep.ladder.entity.ladder.*;
import nextstep.ladder.util.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResultView {

    static final String HEIGHT_PRINT_SYMBOL = "|";
    static final String DRAW_LINE = "-----";
    static final String NONE_DRAW_LINE = "     ";
    static final String LINE_SEPARATOR = System.lineSeparator();
    static final String LADDER_RESULT_MESSAGE = "사다리 결과";
    static final String GAME_RESULT_MESSAGE = "실행결과";

    private ResultView() {
    }

    private static String rewardFormat(String username) {
        return String.format("%-6s", username);
    }

    public static void printLadderResult(List<String> userNames, Ladder ladder) {
        printStart();
        printUser(userNames);
        printLadder(ladder);
    }

    public static void printStart() {
        System.out.println(LINE_SEPARATOR + LADDER_RESULT_MESSAGE + LINE_SEPARATOR);
    }

    public static void printUser(List<String> userNames) {
        userNames.stream().map(ResultView::rewardFormat).forEach(System.out::print);
        System.out.println();
    }

    public static void printLadder(Ladder ladder) {
        for (LinesOfOneHeight linesOfOneHeight : ladder.getLines()) {
            printOneWidth(linesOfOneHeight);
        }
    }

    private static void printOneWidth(LinesOfOneHeight linesOfOneHeight) {
        int lineSize = linesOfOneHeight.getLineSize();

        IntStream.range(0, lineSize - 1)
                .mapToObj(linesOfOneHeight::positionLine)
                .forEach(ResultView::printLine);


        System.out.println(HEIGHT_PRINT_SYMBOL);
    }

    private static void printLine(Line line) {
        System.out.print(HEIGHT_PRINT_SYMBOL);
        System.out.print(width(line));
    }

    private static String width(Line line) {

        if (line.lineDirection().equals(Direction.RIGHT)) {
            return DRAW_LINE;
        }

        return NONE_DRAW_LINE;
    }

    public static void printSingleUserGameResult(List<GameResult> gameResults) {

        System.out.println(LINE_SEPARATOR + GAME_RESULT_MESSAGE);

        for (GameResult gameResult : gameResults) {
            System.out.println(gameResult.getReward());
        }
    }

    public static void printAllUserGameResult(List<GameResult> gameResults) {

        System.out.println(LINE_SEPARATOR + GAME_RESULT_MESSAGE);

        for (GameResult gameResult : gameResults) {
            System.out.println(gameResult.getName() + Constants.USER_AND_POSITION_APPEND_STRING + gameResult.getReward());
        }
    }

    public static void printReward(String inputLadderResult) {
        Arrays.stream(StringUtil.splitCommaByString(inputLadderResult))
                .map(ResultView::rewardFormat)
                .forEach(System.out::print);

        System.out.println();
    }
}
