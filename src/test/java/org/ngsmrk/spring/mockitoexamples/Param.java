package org.ngsmrk.spring.mockitoexamples;

/**
 * .
 *
 * @author Initial: amark
 * @version 1.0
 */
public class Param {

        final String value;

        Param(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Param param = (Param) o;

            if (!value.equals(param.value)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }
    }
