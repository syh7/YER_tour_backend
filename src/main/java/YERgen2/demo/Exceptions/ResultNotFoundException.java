package YERgen2.demo.Exceptions;

import YERgen2.demo.model.Discipline;

public class ResultNotFoundException extends RuntimeException {
    public ResultNotFoundException(long id) {
        super("Could not find result: " + id);
    }
    public ResultNotFoundException(Discipline discipline, int playerLevel) {
        super("Could not find result for discipline " + discipline + " and playerlevel " + playerLevel);
    }
    public ResultNotFoundException(long tournamentId, Discipline discipline, int playerLevel) {
        super("Could not find result in tournament " + tournamentId + " for discipline " + discipline + " and playerlevel " + playerLevel);
    }
}
