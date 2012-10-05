package eu.stratosphere.nephele.services.memorymanager;

import java.util.List;

import eu.stratosphere.nephele.template.AbstractInvokable;

public interface DynamicMemoryManager extends MemoryManager {

	void allocateAdditionalPages(final AbstractInvokable owner, final List<MemorySegment> target, final int numPages)
			throws MemoryAllocationException;
}
