    package Entity;

    import javax.swing.JComboBox;

public class DisplayvalueModel {

    public class DisplayValue {
        private String displayMember;
        private Object displayValue;

        public DisplayValue(String displayMember, Object displayValue) {
            this.displayMember = displayMember;
            this.displayValue = displayValue;
        }

        public String getDisplayMember() {
            return displayMember;
        }

        public Object getDisplayValue() {
            return displayValue;
        }

        @Override
        public String toString() {
            return displayMember;
        }
    }

    public DisplayValue createDisplayValue(String displayMember, Object displayValue) {
        return new DisplayValue(displayMember, displayValue);
    }

    public void setSelectedCombobox(String cbbselected, JComboBox cbb) {
        for (int i = 0; i < cbb.getItemCount(); i++) {
            Object obj = cbb.getItemAt(i);
            if (obj != null && obj instanceof DisplayValue) {
                DisplayValue m = (DisplayValue) obj;
                if (cbbselected.trim().equals(m.getDisplayMember())) {
                    cbb.setSelectedItem(m);
                }
            }
        }
    }
}
