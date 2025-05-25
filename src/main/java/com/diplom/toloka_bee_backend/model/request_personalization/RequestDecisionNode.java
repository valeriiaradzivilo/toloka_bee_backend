package com.diplom.toloka_bee_backend.model.request_personalization;

import com.diplom.toloka_bee_backend.model.RequestModel;

import java.util.function.Predicate;

public class RequestDecisionNode {
    public RequestDecisionNode yes;
    public RequestDecisionNode no;

    public Predicate<RequestModel> condition;

    public RequestDecisionNode(Predicate<RequestModel> condition) {
        this.condition = condition;
    }

    public boolean evaluate(RequestModel request) {
        return condition.test(request);
    }
}