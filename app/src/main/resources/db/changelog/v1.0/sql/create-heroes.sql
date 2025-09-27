CREATE TABLE IF NOT EXISTS heroes (
    id              UUID    PRIMARY KEY DEFAULT gen_random_uuid(),
    name            TEXT    NOT NULL,
    hp              INT     NOT NULL CHECK (hp > 0),
    base_damage     INT     NOT NULL CHECK (base_damage >= 0)
    );
