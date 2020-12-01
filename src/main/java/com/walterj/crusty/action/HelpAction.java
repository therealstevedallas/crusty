package com.walterj.crusty.action;

import com.walterj.util.Version;
import com.walterj.util.VersionTool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Walter Jordan
 */
public class HelpAction extends BaseAction {

    private static final Logger LOG = LogManager.getLogger(HelpAction.class.getName());
    private List<Version> versions = new ArrayList<>();

    /**
     * <p>Getter for the field <code>versions</code>.</p>
     *
     * @return a {@link List} object.
     */
    public List<Version> getVersions() {

        if (versions.isEmpty()) {
            VersionTool vt = new VersionTool();
            List<String> names = vt.getProductNames();
            for (String n : names) {
                Version v = vt.getProductVersion(n);
                versions.add(v);
            }
        }
        return versions;
    }


    /**
     * <p>Setter for the field <code>versions</code>.</p>
     *
     * @param versions a {@link List} object.
     */
    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    @Override public String perform(HttpSession session, HttpServletRequest request) {
        LOG.debug("perform(): Called");
        return SUCCESS;
    }
}