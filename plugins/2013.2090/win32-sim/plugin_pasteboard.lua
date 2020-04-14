local Library = require "CoronaLibrary"

-- Create library
local lib = Library:new{ name = 'plugin.pasteboard', publisherId = 'com.coronalabs' }

local function showUnsupportedMessage()
	native.showAlert( 'Not Supported', 'The pasteboard plugin is not supported on the simulator, please build for an iOS or Android device', { 'OK' } )
end

-- pasteboard.setAllowedTypes
function lib.setAllowedTypes()
	showUnsupportedMessage()
end

-- pasteboard.getType
function lib.getType()
	showUnsupportedMessage()
end

-- pasteboard.copy
function lib.copy()
	showUnsupportedMessage()
end

-- pasteboard.paste
function lib.paste()
	showUnsupportedMessage()
end

-- pasteboard.clear
function lib.clear()
	showUnsupportedMessage()
end

-- Return an instance
return lib
