package foo.study.url.dto;

import foo.study.url.dto.ValidationGroups.NotEmptyGroup;
import foo.study.url.dto.ValidationGroups.PatternCheckGroup;
import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, NotEmptyGroup.class, PatternCheckGroup.class, })
public interface ValidationSequence {
}

