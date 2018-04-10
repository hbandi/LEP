package com.hb.query;

public enum QueryType {

    SPEL{
        @Override
        public String toString() {
            return "Spring expression Evaluation Type";
        }
    },
    OGNL{
        @Override
        public String toString() {
            return "OGNL";
        }
    };

}
