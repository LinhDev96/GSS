package entities;

import org.springframework.data.annotation.Id;

public class State {
	@Id
    private int id;

    private String name;

    private Country country;
}
