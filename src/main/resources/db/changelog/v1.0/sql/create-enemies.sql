CREATE TABLE IF NOT EXISTS enemies (
    id              UUID    PRIMARY KEY DEFAULT gen_random_uuid(),
    hp              INT     NOT NULL CHECK (hp > 0),
    name            TEXT    NOT NULL,
    base_damage     INT     NOT NULL CHECK (base_damage >= 0)
);