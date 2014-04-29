package enums;

/**
 * Label Category enum, used for storing arrays of Labels from which only one is
 * compultory to be filled in form.
 *
 * @author Azathoth
 */
public enum LabelCategory {

    /**
     * Enum keeping array with Label.ID, Label.PASSPORT, during filling
     * electronic application, only content of one of these Labels has to be
     * filled.
     */
    ID_NUMBER(new Label[]{Label.ID, Label.PASSPORT}),;

    private final Label[] list;                                                 //list musí být synchronizovaný se sloupcem v enumu Label

    private LabelCategory(Label[] list) {
        this.list = list;
    }

    /**
     *
     * @return array of Labels in this enum
     */
    public Label[] getList() {
        return list;
    }

    /**
     * Determines whether any of enums from this class contains provided Label.
     *
     * @param label whose presence in any of enums from this class is searched
     * for
     * @return true if any of enums from this class contains provided Label,
     * otherwise, return false
     */
    public static boolean contains(Label label) {
        for (LabelCategory labelCategory : LabelCategory.values()) {
            for (Label label1 : labelCategory.list) {
                if (label1.equals(label)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns enum containing provided Label, for avoiding Exception, is
     * recommended to use only when static contains method returns true for
     * provided label.
     *
     * @param label whose LabelCategory will be returned
     * @return LabelCategory containing provided Label
     */
    public static LabelCategory containing(Label label) {
        for (LabelCategory labelCategory : LabelCategory.values()) {
            for (Label label1 : labelCategory.list) {
                if (label1.equals(label)) {
                    return labelCategory;
                }
            }
        }
        throw new IllegalArgumentException("No LabelCategory found for " + label.getNameRaw());
    }
}
