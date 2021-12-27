package shift.sextiarysector;

import shift.sextiarysector.creativemodetab.SSCoreCreativeModeTab;

public class SSTabs {

    /**
     * コア
     */
    public static final SSCoreCreativeModeTab TAB_CORE = new SSCoreCreativeModeTab("core", SSItems.LEAF::get);


    /**
     * 水産
     */
    public static final SSCoreCreativeModeTab TAB_FISHERIES = new SSCoreCreativeModeTab("fisheries", SSItems.SQUID_SASHIMI::get);


}
