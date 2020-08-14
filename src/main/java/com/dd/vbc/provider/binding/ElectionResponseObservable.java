package com.dd.vbc.provider.binding;

import com.dd.vbc.network.ElectionResponse;
import javafx.beans.property.ObjectPropertyBase;

public class ElectionResponseObservable extends ObjectPropertyBase<ElectionResponse> {

    public ElectionResponseObservable() {}
    public ElectionResponseObservable(ElectionResponse electionResponse) {
        super(electionResponse);
    }

    @Override
    public Object getBean() {
        return super.get();
    }

    @Override
    public String getName() {
        return ElectionResponse.class.getSimpleName();
    }

    @Override
    public void set(ElectionResponse electionResponse) {
        super.set(electionResponse);
    }
}
