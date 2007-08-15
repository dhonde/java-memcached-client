package net.spy.memcached.protocol.binary;

import net.spy.memcached.ops.DeleteOperation;
import net.spy.memcached.ops.OperationCallback;
import net.spy.memcached.ops.OperationStatus;

class DeleteOperationImpl extends OperationImpl implements
		DeleteOperation {

	private static final int CMD=4;

	private final String key;

	public DeleteOperationImpl(String k, OperationCallback cb) {
		super(CMD, generateOpaque(), cb);
		key=k;
	}

	@Override
	public void initialize() {
		prepareBuffer(key, EMPTY_BYTES);
	}

	@Override
	protected OperationStatus getStatusForErrorCode(int errCode, byte[] errPl) {
		return errCode == ERR_NOT_FOUND ? NOT_FOUND_STATUS : null;
	}

}
