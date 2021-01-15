package com.restproject.bowlinggame.helper;

import com.google.common.base.Predicate;
import com.restproject.bowlinggame.domain.Frame;

public class PlayedFramePredicate implements Predicate<Frame> {

    @Override
    public boolean apply(final Frame frame) {
        return frame.hasBeenPlayed();
    }
}
